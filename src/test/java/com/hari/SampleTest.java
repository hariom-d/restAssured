package com.hari;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.models.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

  @Test
  @Disabled("Disabled this for a time being")
  public void getTest1() {
    // given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/api";
    Response res = RestAssured.get("/users?page=2");
    System.out.println(res.getBody().asString());
    assertEquals(200, res.getStatusCode());
    Root rootObj =res.as(Root.class);
    for (Iterator<Datum> iterator = rootObj.getData().iterator(); iterator.hasNext();){
      Datum datum = (Datum)iterator.next();
       System.out.println(datum.getId());
      System.out.println(datum.getEmail());
      System.out.println(datum.getFirst_name());
      System.out.println(datum.getLast_name());
      System.out.println(datum.getAvatar());
      System.out.println("---------------------------");
    }

  }

  @Test
  @Disabled("Disabled this for a time being")
  public void getTest2() {
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/api";
    Response res = RestAssured.get("/users?page=2");
    Root rootObj = RestAssured.get("/users?page=2").as(Root.class);
    //You can use rootObj.getTotal instead of rootObj.total.
    rootObj.setTotal(rootObj.total+1);
    System.out.println("rootObj.toString()="+rootObj.toString());
    System.out.println("Email="+ rootObj.getData().get(1).getEmail());
    assertEquals(200, res.getStatusCode());
    //Test the Builder pattern
    System.out.println("Test the Builder pattern");
    Support supportObj = Support.builder().url("SupportUrl").text("SupportText").build();
    List<Datum> datumList = Arrays.asList(new Datum[]{Datum.builder().id(1).email("a@a.com") .first_name("abc1").last_name("xyz1").avatar("Krishna1").build(),
            Datum.builder().id(2).email("b@b.com").first_name("abc2").last_name("xyz2").avatar("Krishna2").build()
    });
    Root rootObj2 = Root.builder().page(1).per_page(2).total(3).total_pages(4)
            .support(supportObj)
            .data(datumList)
            .build();
    System.out.println("rootObj2.toString()= "+rootObj2.toString());
  }

  @Test
//  @Disabled("Disabled this for a time being")
  public void postTest() throws JsonProcessingException {
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/api";
//    UserPostReq user = new UserPostReq();
//    user.setName("hari");
//    user.setJob("computers");

    UserPostReq user = UserPostReq.builder()
            .name("Hari")
            .job("Computers")
            .build();


    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String postReqJson = mapper.writeValueAsString(user);
    System.out.println("post request= "+ postReqJson);
    RequestSpecification request= RestAssured.given();
//    Response res  = request.body(user).header("Content-Type", "application/json").post("/users");
//    Response res  = request.body(user).contentType("application/json").post("/users");
    request = request.body(postReqJson).contentType("application/json");
    Response res  = request.post("/users");
    System.out.println("post response= "+ res.getBody().asString());
    assertEquals(201, res.getStatusCode());
    UserPostResp userPostResp = res.getBody().as(UserPostResp.class);
    System.out.println("userPostResp.toString()= "+userPostResp.toString());
  }
}
