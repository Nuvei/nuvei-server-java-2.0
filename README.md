# Nuvei REST API SDK for Java.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.safecharge/safecharge-sdk-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.safecharge/safecharge-sdk-java)
[![codecov](https://codecov.io/gh/SafeChargeInternational/safecharge-java/branch/development/graph/badge.svg)](https://codecov.io/gh/SafeChargeInternational/safecharge-java)

Nuvei’s REST API SDK for Java provides developer tools for accessing Nuvei's REST API. Nuvei’s REST API is a simple, easy to use, secure and stateless API, which enables online merchants and service providers to process consumer payments through Nuvei’s payment gateway. The API supports merchants of all levels of PCI certification, from their online and mobile merchant applications, and is compatible with a large variety of payment options, i.e. payment cards, alternative payment methods, etc. For Nuvei REST API documentation, please see: https://docs.nuvei.com/api/main

## Requirements

Java 1.7 or later.

### Maven

If your application is going to be deployed on a Java EE server add the following dependency:

```xml
<dependency>    
    <groupId>com.nuvei</groupId>
    <artifactId>nuvei-sdk-java</artifactId>
    <version>1.7.10</version>
</dependency>
```

If you are going to use the SDK in Java SE or a non Java EE server please add a Bean Validation API Implementation dependency. Certified implementations of Bean Validation API can be found here: http://beanvalidation.org/certified/ (v.1.1 implementation required).

For example:

 ```xml
<dependencies>
    ...
    <dependency>    
        <groupId>com.nuvei</groupId>
        <artifactId>nuvei-sdk-java</artifactId>
        <version>1.7.5</version>
    </dependency>
    <dependency>
        <groupId>org.apache.bval</groupId>
        <artifactId>bval-jsr</artifactId>
        <version>1.1.2</version>
    </dependency>
</dependencies>
 ```

## Running your first request

You only need to setup a HTTP Client and to provide the Nuvei API host to the request executor and then you can start building requests and send them to the Nuvei API.

Check how simple it is trough this sample:

```java
import com.nuvei.biz.NuveiRequestExecutor;
import com.nuvei.model.MerchantInfo;
import com.nuvei.request.GetSessionTokenRequest;
import com.nuvei.request.NuveiRequest;
import com.nuvei.response.NuveiResponse;
import com.nuvei.util.APIConstants;
import com.nuvei.util.Constants;

public class Sample {

    public static void main(String[] args) {
        NuveiRequestExecutor requestExecutor = NuveiRequestExecutor.getInstance();

        MerchantInfo merchantInfo = new MerchantInfo("MERCHANT_KEY_PROVIDED_BY_NUVEI", "MERCHANT_ID_PROVIDED_BY_NUVEI",
                "MERCHANT_SITE_ID_PROVIDED_BY_NUVEI", APIConstants.INTEGRATION_HOST, Constants.HashAlgorithm.SHA256);

        NuveiRequest nuveiRequest = GetSessionTokenRequest.builder()
                                                                    .addMerchantInfo(merchantInfo)
                                                                    .build();

        NuveiResponse response = requestExecutor.executeRequest(nuveiRequest);
        System.out.println("Received sessionToken = " + response.getSessionToken());
    }
}
```
More samples tests can be found [here](https://github.com/Nuvei/nuvei-server-java/tree/master/src/test/java/com/nuvei/test/workflow/).
