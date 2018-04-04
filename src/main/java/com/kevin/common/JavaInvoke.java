package com.kevin.common;

import com.kevin.common.domain.People;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanItemCreate;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemCreateParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemCreateResult;
import org.nutz.lang.Mirror;

/**
 * Created by kevin on 2017/12/29.
 */
public class JavaInvoke {
    public static void main(String[] args) {
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people,"setName","tom");
        System.out.println(peopleMirror.invoke(people,"getName"));
        System.out.println(peopleMirror.invoke(people, "doSomething", "kevin", "man", 28));

        YZClient client = new DefaultYZClient(new Token("token"));
        YouzanItemCreateParams youzanItemCreateParams = new YouzanItemCreateParams();

        youzanItemCreateParams.setTitle("aaatest-standard5566");
        youzanItemCreateParams.setPrice(10000L);
        youzanItemCreateParams.setImageIds("1025191957");
        youzanItemCreateParams.setDesc("http://n.sinaimg.cn/mil/8_img/upload/f8bc40b5/20170710/-Bec-fyhwret0362019.jpg");
        youzanItemCreateParams.setItemNo("6933285600396");

        YouzanItemCreate youzanItemCreate = new YouzanItemCreate();
        youzanItemCreate.setAPIParams(youzanItemCreateParams);
        YouzanItemCreateResult result = client.invoke(youzanItemCreate);
    }

}
