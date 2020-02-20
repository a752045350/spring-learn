package com.springboot.learn.controller;


import com.springboot.learn.bean.Article;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Cat;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/es")
@RestController
public class ESController {

    @Autowired
    JestClient jestClient;

    @RequestMapping("/test")
    public boolean esTest() {
        Article article = new Article();
        article.setId(3);
        article.setName("小明");
        article.setAuthor("仨打");
        article.setTitle("ashdha");
        Index index = new Index.Builder(article).index("EStest").type("news").id("3").build();

        try {
            JestResult result = jestClient.execute(index);
            if (result == null && !result.isSucceeded()) {
                throw new Exception(result.getErrorMessage());
            }
            System.out.println(result.getResponseCode());
        return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("2222222222222");
            return false;
        }

    }
    /* try{
         Cat cat = new Cat.IndicesBuilder().build();
         JestResult result = jestClient.execute(cat);
         return result.getJsonString();
     }catch (Exception e){
        return "";
     }
*/

}
