
package models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetOrderReq {

    @Builder.Default
    public String id = "0";

    @Builder.Default
    public Integer petId = 0;

    @Builder.Default
    public Integer quantity = 1;

    @Builder.Default
    public String shipDate = "2022-11-15T10:12:36.978Z";

    @Builder.Default
    public String status = "placed";

    @Builder.Default
    public Boolean complete = true;

}
