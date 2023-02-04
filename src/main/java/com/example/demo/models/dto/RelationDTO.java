package com.example.demo.models.dto;

import com.example.demo.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationDTO {
    User username;
    User follwer;
}
