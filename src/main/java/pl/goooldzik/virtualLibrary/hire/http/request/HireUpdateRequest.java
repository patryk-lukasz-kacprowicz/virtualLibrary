package pl.goooldzik.virtualLibrary.hire.http.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.goooldzik.virtualLibrary.hire.enums.HireStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HireUpdateRequest {
    private HireStatusEnum status;

    public HireStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HireStatusEnum status) {
        this.status = status;
    }
}
