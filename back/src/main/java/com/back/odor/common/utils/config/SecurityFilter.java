package com.back.odor.common.utils.config;

import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "securityFilter", urlPatterns = "/*")
public class SecurityFilter extends DelegatingFilterProxy implements Filter {



}
