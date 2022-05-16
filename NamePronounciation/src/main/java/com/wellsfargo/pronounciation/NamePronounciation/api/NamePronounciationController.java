package com.wellsfargo.pronounciation.NamePronounciation.api;

import java.util.List;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.wellsfargo.pronounciation.NamePronounciation.model.*;
import com.wellsfargo.pronounciation.NamePronounciation.service.*;

@Controller
class NamePronounciationController {

    @Autowired
    NamePronounciation namePronounciation;

    @RequestMapping(value="/pronounciation/v1",method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<PronounciationDetails> create(@RequestBody PronounciationDetails details) {

        Gson gson = new Gson();
        String json = gson.toJson(details);
        System.out.println(json);
        namePronounciation.pronounceMessage(details);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

}