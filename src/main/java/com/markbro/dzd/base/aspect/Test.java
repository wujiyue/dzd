package com.markbro.dzd.base.aspect;

import com.markbro.asoiaf.core.exception.ApplicationException;
import com.markbro.dzd.base.account.bean.SecurityQueston;
import com.markbro.dzd.base.account.service.SecurityQuestonService;
import com.markbro.dzd.common.util.Guid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016-12-28.
 */
@Component
public class Test {
    @Autowired
    SecurityQuestonService securityQuestonService;
    @Autowired
   // QueueSender queueSender;
    public void sleep(){
        System.out.println("=======sleep============");
    }
    @Transactional
    public void testEx(){
        System.out.println("=======testEx============");
        SecurityQueston q=new SecurityQueston();
        q.setId(Guid.get());
        q.setQuestion("testEx");
        securityQuestonService.add(q);
        throw new RuntimeException("testEx!");
    }
    @Transactional
    public void testEx2(){
        System.out.println("=======testEx2============");
        SecurityQueston q=new SecurityQueston();
        q.setId(Guid.get());
        q.setQuestion("testEx2");
        securityQuestonService.add(q);
        throw new ApplicationException("testEx2!");
    }
   // public void testSendQueueMsg(){
   //     queueSender.send("FirstQueue","test FirstQueue  send msg!!!");
   // }

}
