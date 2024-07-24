package id.demo.modules.handle.controller;



import cn.teleinfo.idhub.manage.doip.server.domain.DoipReturn;
import id.demo.modules.handle.service.ResolveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/handle")
@RequiredArgsConstructor
public class ApiController {
    

    @Autowired
    private ResolveService resolveService;

    /**
     * 标识数据查询demo
     *
     * @param handle
     * @return
     */
    @GetMapping("/resolve")
    private Object dataResolve(@RequestParam(name = "handle") String handle)  {
        Object data = resolveService.resolve(handle);
        return data;
    }
    

    /**
     * 大屏追溯查询接口
     * 
     * @param handle
     * @param id
     * @return
     */
    @GetMapping("/query")
    private DoipReturn query(@RequestParam(name = "handle" ,required = false) String handle , @RequestParam(name = "id" ,required = false,defaultValue = "8") Integer id)  {
        DoipReturn resolve = resolveService.resolveEx(handle, id);
        return resolve;
    }
    


}
