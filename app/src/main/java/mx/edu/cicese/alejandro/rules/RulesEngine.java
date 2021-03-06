package mx.edu.cicese.alejandro.rules;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import mx.edu.cicese.alejandro.voicehelper.R;

/**
 * Created by Alejandro on 3/2/15.
 * TODO
 */
public class RulesEngine {
    ArrayList<Mistep> arrayList;
    Context context;


    public RulesEngine(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<Mistep>();
    }

    public Mistep addMistep(Mistep.Kind kind) {
        Long current = System.currentTimeMillis() / 1000;

        Mistep newMistep = new Mistep(kind, current);

        newMistep.setTriggerMessege(selectTringgerString(kind));
        newMistep.setRulesMessege(selectRulesString(kind));

        newMistep.setNumberOfIncident(getNumberOfMistep(kind) + 1);

        Log.d("VoiceHelper", newMistep.getTriggerMessege());

        /** parte para agregar**/
        arrayList.add(newMistep);

        return newMistep;
    }

    public int getNumberOfMistep(Mistep.Kind kind) {
        int count = 0;
        for (Mistep Mistep : arrayList) {
            if (Mistep.kindOf == kind)
                count++;
        }
        return count;
    }


    public String getTriggerMessage(Mistep.Kind kind, int tier) {
        String[] strings = null;
        String message;
        Resources res = context.getResources();
        switch (kind) {
            case EMOTION:
                switch (tier) {
                    case 1:
                        strings = res.getStringArray(R.array.emotion_trigger_first);
                        break;
                    case 2:
                        strings = res.getStringArray(R.array.emotion_trigger_second);
                        break;
                    case 3:
                        strings = res.getStringArray(R.array.emotion_trigger_third);
                        break;
                    default:
                        strings = null;
                }
                break;
            case VOICE:
                switch (tier) {
                    case 1:
                        strings = res.getStringArray(R.array.voice_rules_first);
                        break;
                    case 2:
                        strings = res.getStringArray(R.array.voice_rules_second);
                        break;
                    case 3:
                        strings = res.getStringArray(R.array.voice_rules_third);
                        break;
                    default:
                        strings = null;
                }
                break;
            case FLUENCY:
                switch (tier) {
                    case 1:
                        strings = res.getStringArray(R.array.fluency_rules_first);
                        break;
                    case 2:
                        strings = res.getStringArray(R.array.fluency_rules_second);
                        break;
                    case 3:
                        strings = res.getStringArray(R.array.fluency_rules_third);
                        break;
                    default:
                        strings = null;
                }
                break;
            default:
                strings = null;
        }
        if (strings != null)
            message = strings[new Random().nextInt(strings.length)];
        else
            message = res.getString(R.string.cicese_placeholder);

        return message;
    }

    /**
     * Revisar... con quedo muy limpio que digamos
     */
    public String selectTringgerString(Mistep.Kind kind) {
        int numberOfIncidents = getNumberOfMistep(kind);
        Resources res = context.getResources();
        String[] strings = null;

        if (numberOfIncidents <= 1) {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_trigger_first);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_trigger_first);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_trigger_first);
            }
        } else if (numberOfIncidents <= 3) {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_trigger_second);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_trigger_second);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_trigger_second);
            }
        } else {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_trigger_third);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_trigger_third);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_trigger_third);
            }
        }
        return strings[new Random().nextInt(strings.length)];
    }

    /**
     * --Replicado de Trigger-- Revisar... con quedo muy limpio que digamos
     */
    public String selectRulesString(Mistep.Kind kind) {
        int numberOfIncidents = getNumberOfMistep(kind);
        Resources res = context.getResources();
        String[] strings = null;

        if (numberOfIncidents <= 1) {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_rules_first);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_rules_first);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_rules_first);
            }
        } else if (numberOfIncidents <= 3) {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_rules_second);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_rules_second);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_rules_second);
            }
        } else {
            if (kind == Mistep.Kind.VOICE) {
                strings = res.getStringArray(R.array.voice_rules_third);
            }
            if (kind == Mistep.Kind.FLUENCY) {
                strings = res.getStringArray(R.array.fluency_rules_third);
            }
            if (kind == Mistep.Kind.EMOTION) {
                strings = res.getStringArray(R.array.emotion_rules_third);
            }
        }


        return strings[new Random().nextInt(strings.length)];
    }
}
