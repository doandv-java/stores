package doan.stores.dto.dxo;

import doan.stores.enums.EGender;
import doan.stores.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDxo {

    private String username;

    private String password;

    private String name;

    private RoleEnum role;

    private EGender gender;

}
