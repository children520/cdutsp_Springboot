package com.xiaojun.cdutspspringboot.controller;

import com.xiaojun.cdutspspringboot.enity.CardMessage;
import com.xiaojun.cdutspspringboot.repository.CardMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardMessageController {
    @Autowired
    private CardMessageRepository cardMessageRepository;
    @PostMapping(path = "/commit")
    public @ResponseBody String commitCardMessage(@RequestBody CardMessage cardMessage){
        try{
            cardMessageRepository.save(cardMessage);
            return "发布成功";
        }catch (Exception e){
            System.out.println(e);
            return "发布失败";
        }
    }
    @GetMapping(path = "/all")
    public @ResponseBody List<CardMessage> getAllCardMessage(){
        return cardMessageRepository.findAll();
    }

}
