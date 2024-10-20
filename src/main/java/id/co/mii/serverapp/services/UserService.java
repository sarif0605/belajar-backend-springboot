package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.User;
import id.co.mii.serverapp.repositories.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;
  // private ModelMapper modelMapper;
  // private RoleService roleService;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(Integer id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!"));
  }

  // public User create(UserRequest userRequest) {
  // Employee employee = modelMapper.map(userRequest, Employee.class);
  // User user = modelMapper.map(userRequest, User.class);

  // employee.setUser(user);
  // user.setEmployee(employee);

  // // set default role
  // List<Role> roles = new ArrayList<>();
  // roles.add(roleService.getById(2));
  // user.setRoles(roles);

  // return userRepository.save(user);
  // }

  public User update(Integer id, User user) {
    getById(id);
    user.setId(id);
    return userRepository.save(user);
  }

  public User delete(Integer id) {
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }
}
