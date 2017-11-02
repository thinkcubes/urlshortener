package kr.co.freeism.urlshortener.controller;

import kr.co.freeism.urlshortener.service.UrlMapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Martin
 * @since 2017. 11. 2.
 */
@Slf4j
@Controller
@Validated
public class ShortenUrlController {
    @Autowired
    private UrlMapService urlMapService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView form(@RequestParam(value = "url", required = false) @URL String url) {
        ModelAndView modelAndView = new ModelAndView("form");

        if (StringUtils.isNotEmpty(url)) {
            modelAndView.addObject("hashValue", urlMapService.getHashValue(url));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/{hashValue}", method = RequestMethod.GET)
    public RedirectView redirect(@PathVariable String hashValue) {
        return new RedirectView(urlMapService.getUrl(hashValue));
    }
}