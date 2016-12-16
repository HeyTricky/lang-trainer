package com.dsr.javaschool.langtrainer.service;


import com.dsr.javaschool.langtrainer.utils.YandexTranslator;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TranslateService {

    public String translateEnglishWord(String word) throws IOException {
        return YandexTranslator.translate("en-ru", word);
    }

    public String translateRussianWord(String word) throws IOException {
        return YandexTranslator.translate("ru-en", word);
    }
}
