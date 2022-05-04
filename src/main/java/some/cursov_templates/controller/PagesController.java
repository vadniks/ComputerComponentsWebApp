package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import some.cursov_templates.service.IPresenter;
import some.cursov_templates.service.IndexPageService;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Controller
public class PagesController implements IPresenter {
    private final IndexPageService indexService;

    @GetMapping(ENDPOINT_INDEX)
    public String index(Model model, HttpServletRequest request) {
        val a = new HashMap<String, String>();
        a.put("image", "image");
        a.put("name", "name");
        a.put("cost", "cost");
        a.put("details", "details");
        model.addAttribute(ATTRIBUTE_OVERVIEW_ITEMS, List.of(a));
        return PAGE_INDEX;
    }

    @GetMapping(ENDPOINT_BROWSE)
    public String browse() {
        return PAGE_BROWSE;
    }

    @GetMapping(ENDPOINT_LOGIN)
    public String login() {
        return PAGE_LOGIN;
    }

    @GetMapping(ENDPOINT_REGISTER)
    public String register() {
        return PAGE_REGISTER;
    }

    @GetMapping(ENDPOINT_ADMIN)
    public String admin() {
        return PAGE_ADMIN;
    }
}
