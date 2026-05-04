public class AnomalyManager {

    public static void generate(GamePanel panel, int period) {

        // PERIOD 0 SAFE
        if (period == 0) {
            panel.currentBase = BaseState.NORMAL;
            panel.overlays.clear();
            return;
        }

        // CLEAR OLD OVERLAYS
        panel.overlays.clear();

        // ================= BASE SELECTION =================
        int baseRoll = (int)(Math.random() * 100);

        // ================= WEIGHTED BASE SYSTEM =================
        // (the game was too easy...)
        if (baseRoll < 45) {
            panel.currentBase = BaseState.NORMAL;
        }
        else if (baseRoll < 70) {
            panel.currentBase = BaseState.EYES;
        }
        else if (baseRoll < 90) {
            panel.currentBase = BaseState.NO_BATHROOM;
        }
        else if (baseRoll < 98) {
            panel.currentBase = BaseState.RED;
        }
        else {
            panel.currentBase = BaseState.RED_EYES;
        }

        // ================= OVERLAYS =================
        int overlayCount = (int)(Math.random() * 3); // 0–2 overlays

        for (int i = 0; i < overlayCount; i++) {

            int o = (int)(Math.random() * 5);

            switch (o) {

                case 0 -> panel.overlays.add(new BathroomSpillAnomaly());
                case 1 -> panel.overlays.add(new BulletinBoardAnomaly());
                case 2 -> panel.overlays.add(new LockerBloodAnomaly());
                case 3 -> panel.overlays.add(new MopSpillAnomaly());
                case 4 -> panel.overlays.add(new WindowPersonAnomaly());
            }
        }
    }
}