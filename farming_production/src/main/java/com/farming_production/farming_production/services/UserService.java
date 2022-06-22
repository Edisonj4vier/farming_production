package com.farming_production.farming_production.services;

import java.util.List;
import com.farming_production.farming_production.dto.NewUserDTO;
import com.farming_production.farming_production.dto.UserDTO;

public interface UserService {

    public UserDTO create(NewUserDTO userDTo);
    public UserDTO retrieve(Long id);
    public UserDTO update(UserDTO userDTO, Long id);
    public void delete(Long id);

    public List<UserDTO> list();
}
