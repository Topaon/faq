package com.example.faq.entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Faq {
	String theme;
	ArrayList<String> questions;
	ArrayList<String> reponses;
}