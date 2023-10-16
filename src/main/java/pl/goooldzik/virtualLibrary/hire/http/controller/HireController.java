package pl.goooldzik.virtualLibrary.hire.http.controller;

import org.springframework.web.bind.annotation.*;
import pl.goooldzik.virtualLibrary.hire.dto.HireDTO;
import pl.goooldzik.virtualLibrary.hire.http.request.HireStoreRequest;
import pl.goooldzik.virtualLibrary.hire.http.request.HireUpdateRequest;
import pl.goooldzik.virtualLibrary.hire.model.Hire;
import pl.goooldzik.virtualLibrary.hire.service.HireService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hire")
public class HireController {
    private final HireService hireService;

    public HireController(HireService hireService) {
        this.hireService = hireService;
    }

    @GetMapping
    public List<HireDTO> index(@RequestParam(name = "status", required = false, defaultValue = "any") String status) {
        return this.hireService.index(status);
    }

    @GetMapping(path = "{hireId}")
    public Hire show(@PathVariable Long hireId) {
        return this.hireService.show(hireId);
    }

    @PostMapping
    public void store(@RequestBody HireStoreRequest request) {
        this.hireService.store(request);
    }

    @DeleteMapping(path = "{hireId}")
    public boolean destroy(@PathVariable Long hireId) {
        return this.hireService.destroy(hireId);
    }

    @PatchMapping
    public void changeStatus(@PathVariable Long hireID, @RequestBody HireUpdateRequest request) {
        this.hireService.changeStatus(hireID, request);
    }
}
