package some.cursov_templates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import some.cursov_templates.entity.OverviewItem;
import some.cursov_templates.service.IPresenter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static some.cursov_templates.Constants.*;

@Controller
public class PagesController implements IPresenter {

    @GetMapping(ENDPOINT_INDEX)
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("items", List.of(
            new OverviewItem("image", "", "cost", "details")));
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
