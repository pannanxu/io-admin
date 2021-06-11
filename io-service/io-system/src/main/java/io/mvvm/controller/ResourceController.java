package io.mvvm.controller;

import io.mvvm.model.Ret;
import io.mvvm.model.vo.ResourceVO;
import io.mvvm.service.IResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: io-admin
 * @description:
 * @author: Mr. Pan
 * @create: 2021-06-11 21:19
 **/
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Resource
    private IResourceService iResourceService;

    @GetMapping("/menus/list")
    public Ret<List<ResourceVO>> getMenus() {
        List<ResourceVO> menus = iResourceService.getResourceListByType(1);
        return Ret.success(menus);
    }

}
