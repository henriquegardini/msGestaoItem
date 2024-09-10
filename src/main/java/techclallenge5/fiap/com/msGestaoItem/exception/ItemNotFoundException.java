package techclallenge5.fiap.com.msGestaoItem.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("O item não foi encontrado!");
    }
}
