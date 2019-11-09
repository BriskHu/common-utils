package com.briskhu.common.jgui.other;


import java.util.HashMap;
import java.util.Map;

/**
 * 面板上下文<p/>
 *
 * @author Brisk Hu
 * created on 2019-11-04
 **/
public class GuiContext {

    private Map<String, Object> keyInfoMap = new HashMap<>();

    /**
     * 保存现场
     *
     * @param elementName
     * @param element
     */
    public void saveLocale(String elementName, Object element) {
        keyInfoMap.put(elementName, element);
    }

    public Map<String, Object> getLocale() {
        return keyInfoMap;
    }

    public Object getElement(String elementName) {
        return keyInfoMap.get(elementName);
    }

}