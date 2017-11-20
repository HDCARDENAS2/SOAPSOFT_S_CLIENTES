/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soapsoft.Util;

public class LlaveValor {
    
    private String key;
    
    private Object value;
    
    public LlaveValor(String key) {
        this.key = key;
    }
    public LlaveValor(String key,Object value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    
}
