package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import some.cursov_templates.service.ComponentsService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.Constants.STATUS_OK;

@RequiredArgsConstructor
@Controller
public class RestController {
    @ImplicitAutowire
    private final ComponentsService componentsService;

    @PostMapping(ENDPOINT_SELECT)
    public EmptyResponse select(
        HttpServletRequest request,
        @RequestParam String id
    ) {
        componentsService.setSelection(request, id);
        return STATUS_OK;
    }

    @PostMapping(ENDPOINT_CLEAR)
    public EmptyResponse clear(HttpServletRequest request) {
        componentsService.setSelection(request, null);
        return STATUS_OK;
    }
}
