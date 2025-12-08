package com.paulinasprojects.ppbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDto<T> {
  private List<T> content;
  private Integer page;
  private Integer size;
  private Long totalElements;
  private Integer totalPages;
  private boolean lastPage;
}
