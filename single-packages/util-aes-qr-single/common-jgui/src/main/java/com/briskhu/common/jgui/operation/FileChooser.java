package com.briskhu.common.jgui.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-01
 **/
public class FileChooser {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileChooser.class);

    /* ---------------------------------------- fileds ---------------------------------------- */


    /* ---------------------------------------- methods ---------------------------------------- */

    /**
     * @param title
     * @param fontSize
     * @param approveText
     * @return
     */
    public static JFileChooser initDirChooser(String title, int fontSize, String approveText) {
        JFileChooser dirChooser = initDirChooser(title, fontSize);
        dirChooser.setApproveButtonText(approveText);

        return dirChooser;
    }

    /**
     * @param title
     * @param fontSize
     * @return
     */
    public static JFileChooser initDirChooser(String title, int fontSize) {
        JFileChooser dirChooser = initDirChooser(fontSize);
        dirChooser.setDialogTitle(title);

        return dirChooser;
    }

    /**
     *
     * @param fontSize
     * @return
     */
    public static JFileChooser initDirChooser(int fontSize) {
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setFont(new Font(null, Font.PLAIN, fontSize));
        dirChooser.setPreferredSize(new Dimension(500, 500));
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        return dirChooser;
    }

    /**
     *
     * @param dirChooser
     * @return
     */
    public static int getDirChooserResult(JFileChooser dirChooser) {
        return dirChooser.showOpenDialog(dirChooser);
    }


}


