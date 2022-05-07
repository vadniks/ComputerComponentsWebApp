package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import some.cursov_templates.service.ComponentsService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.Constants.STATUS_OK;

@RequiredArgsConstructor
@Controller
public class RestController {
    @ImplicitAutowire
    private final ComponentsService componentsService;

    @ResponseBody
    @GetMapping(value = GET_COMPONENT, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringPairMap component(@RequestParam String id) {
        return componentsService.getComponent(Integer.parseInt(id));
    }

    @PostMapping(POST_SELECT)
    public EmptyResponse select(
        HttpServletRequest request,
        @RequestParam String id
    ) {
        componentsService.setSelection(request, id);
        return STATUS_OK;
    }

    @PostMapping(POST_CLEAR)
    public EmptyResponse clear(HttpServletRequest request) {
        componentsService.setSelection(request, null);
        return STATUS_OK;
    }
}
