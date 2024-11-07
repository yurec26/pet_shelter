package org.client.util;

import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;


public class FXButton_Effects {

    public static InnerShadow innerShadow = new InnerShadow(15, Color.GOLDENROD);

    public static void addHoverEffect(@NotNull Node node) {
        node.setOnMouseEntered(event -> setEffect(node, innerShadow));
        node.setOnMouseExited(event -> setEffect(node, null));
    }

    public static void setEffect(Node node, Effect effect) {
        node.setEffect(effect);
    }
}
