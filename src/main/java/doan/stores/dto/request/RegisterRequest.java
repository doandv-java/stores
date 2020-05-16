package doan.stores.dto.request;

import doan.stores.dto.dxo.RegisterDxo;
import doan.stores.enums.EGender;
import doan.stores.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    public RegisterDxo toDxo() {
        RegisterDxo dxo = new RegisterDxo();
        dxo.setUsername(StringUtils.trimToEmpty(userName));
        dxo.setName(StringUtils.capitalize(StringUtils.trimToEmpty(name)));
        dxo.setPassword(password);
        dxo.setRole(RoleEnum.ROLE_CUSTOMER);
        dxo.setGender(EGender.MALE);
        return dxo;
    }
}
