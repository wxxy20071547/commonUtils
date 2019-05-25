package com.kevin.common.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @author kevin
 * @date 2019-05-08 15:48
 */
public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );


    public static void main(String[] args) {
        Flux.just("hellow", "why").subscribe(System.out::println);
        Flux.just(words).subscribe(System.out::println);
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);

        Flux.range(1, 100).buffer(20).subscribe(System.out::println);

        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 100).window(20).subscribe(System.out::println);

        Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);

        Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);

        Flux.range(1, 1000).take(10).subscribe(System.out::println);

        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);

        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println); //当 Predicate 返回 true 时才进行提取。

        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);//提取元素直到 Predicate 返回 true。

        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);//对流中的元素进行相加操作，结果为 5050

        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);//相加操作，不过通过一个 Supplier 给出了初始值为 100，所以结果为 5150。

        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }

}
