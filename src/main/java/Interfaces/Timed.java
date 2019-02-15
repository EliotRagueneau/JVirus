package Interfaces;

import Enums.TurnCallBack;

public interface Timed {
    /**
     * Action that has too be made every turn
     *
     * @return A TurnCallBack that will be treated by the map
     */
    TurnCallBack turn();
}
