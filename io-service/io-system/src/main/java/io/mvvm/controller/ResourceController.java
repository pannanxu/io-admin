package io.mvvm.controller;

import io.mvvm.model.Ret;
import io.mvvm.model.vo.PermissionVO;
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
 * @author: 潘南旭
 * @create: 2021-06-11 21:19
 **/
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Resource
    private IResourceService iResourceService;

    @GetMapping("/permissions/list/by/user/role")
    public Ret<List<PermissionVO>> getPermissionsByUserRole() {
        List<PermissionVO> vos = iResourceService.getPermissionsByUserRole();
        return Ret.success(vos);
    }

}
