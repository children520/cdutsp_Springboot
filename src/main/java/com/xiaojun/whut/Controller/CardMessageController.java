package com.xiaojun.whut.Controller;

//import com.xiaojun.cdutspspringboot.repository.CardMessageRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/card")
public class CardMessageController {
//    @Autowired
//    private CardMessageRepository cardMessageRepository;
//    @PostMapping(path = "/commit")
//    public @ResponseBody String commitCardMessage(@RequestBody CardMessage cardMessage){
//        try{
//            cardMessageRepository.save(cardMessage);
//            return "发布成功";
//        }catch (Exception e){
//            System.out.println(e);
//            return "发布失败";
//        }
//    }
//    @GetMapping(path = "/all")
//    public @ResponseBody List<CardMessage> getAllCardMessage(){
//        return cardMessageRepository.findAll();
//    }
//
//    @PostMapping(path = "/update/{id}")
//    public @ResponseBody String updateCardMessage(@PathVariable("id") Integer id,@RequestBody CardMessage cardMessage){
//        CardMessage curCardMessage=cardMessageRepository.findById(id);
//        curCardMessage.setLikeNum(cardMessage.getLikeNum());
//        try {
//            cardMessageRepository.save(curCardMessage);
//            return "更新成功";
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"更新失败",e);
//        }
//    }
//    @DeleteMapping("/delete/{id}")
//    public @ResponseBody String deleteCardMessage(@PathVariable("id") Integer id){
//        try {
//            CardMessage curCardMessage=cardMessageRepository.findById(id);
//            cardMessageRepository.delete(curCardMessage);
//            return "删除成功";
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return "删除失败";
//        }
//    }
}
