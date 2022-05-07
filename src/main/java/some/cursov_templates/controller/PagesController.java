package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.service.ComponentsService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Controller
public class PagesController {
    @ImplicitAutowire
    private final ComponentsService componentsService;

    @GetMapping(ENDPOINT_INDEX)
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute(ATTRIBUTE_ITEMS, componentsService.getOverviewItems(request));
        return PAGE_INDEX;
    }

    @GetMapping(ENDPOINT_BROWSE)
    public String browse(Model model, @RequestParam String type) {
        model.addAttribute(ATTRIBUTE_ITEMS, componentsService.getComponentsByType(type));
        return PAGE_BROWSE;
    }

    @ResponseBody
    @GetMapping(value = ENDPOINT_COMPONENT, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringPairMap component(@RequestParam String id) {
        return componentsService.getComponent(Integer.parseInt(id));
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
