package com.drartgames.stepper.sl.processors;

import com.drartgames.stepper.exceptions.SLRuntimeException;
import com.drartgames.stepper.sl.DefaultLookupUtil;
import com.drartgames.stepper.sl.LookUpUtil;
import com.drartgames.stepper.sl.SLInterpreter;
import com.drartgames.stepper.sl.lang.Action;
import com.drartgames.stepper.sl.lang.Argument;
import com.drartgames.stepper.sl.lang.Operator;
import com.drartgames.stepper.sl.lang.ValueType;
import com.drartgames.stepper.sl.lang.memory.DefaultDialog;
import com.drartgames.stepper.sl.lang.memory.Dialog;

import java.util.List;

public class AddDialogOperatorProcessor extends BaseProcessor implements OperatorProcessor {
    public static final int ADD_DIALOG_ID = 12;
    public static final int ARGS_COUNT = 4;

    private static final String ENABLED = "enabled";
    private static final String DISABLED = "disabled";

    public AddDialogOperatorProcessor() {
        super(ADD_DIALOG_ID);
    }

    @Override
    public void execute(SLInterpreter interpreter, Operator operator) throws SLRuntimeException {
        checkArguments(operator, ValueType.STRING_LITERAL, ValueType.STRING_LITERAL,
                ValueType.GENERAL_LITERAL, ValueType.GENERAL_LITERAL);

        List<Argument> arguments = operator.getArguments();
        String pattern = arguments.get(0).getValue().getStringValue();
        String name = arguments.get(1).getValue().getStringValue();
        String actionName = arguments.get(2).getValue().getGeneralLiteralValue().getStringRepresentation();

        Action action = lookUpUtil.lookupAction(interpreter, actionName);

        //@fixme getStringRepresentation to toString
        String enabled = arguments.get(3).getValue().getGeneralLiteralValue().getStringRepresentation();

        boolean isEnabled;

        switch (enabled) {
            case ENABLED:
                isEnabled = true;

                break;
            case DISABLED:
                isEnabled = false;

                break;
            default:
                throw new SLRuntimeException("Undefined initial state of dialog: " + enabled +
                        " at line " + operator.getCallLineNumber());
        }


        Dialog dialog = new DefaultDialog(name, pattern, action, isEnabled);

        interpreter.getScenesManager().getCurrentScene().getDialogsManager().add(dialog);
    }
}
