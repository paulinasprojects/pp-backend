package com.paulinasprojects.ppbackend.mappers;

import com.paulinasprojects.ppbackend.dtos.SignupRequest;
import com.paulinasprojects.ppbackend.dtos.UpdateUserRequestDto;
import com.paulinasprojects.ppbackend.dtos.UpdateUserResponseDto;
import com.paulinasprojects.ppbackend.dtos.UserDto;
import com.paulinasprojects.ppbackend.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toDto(User user);
  User toEntity(SignupRequest request);
  UpdateUserResponseDto toUpdateUserDto(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateUserEntityFromDto(UpdateUserRequestDto dto, @MappingTarget User user);
}
