package nachos.proj1;

public interface ObserverSystem extends Mediator
{
	void attach(ObservableSystem system);
	void dettach(ObservableSystem system);
}
