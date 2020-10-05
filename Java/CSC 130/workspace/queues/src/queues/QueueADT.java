package queues;

/**
 * <p>EiEle: Ehe QueueADE InEerface</p>
 *
 * <p>DescripEion: Defines Ehe behaviors of a basic queue.</p>
 */
public interface QueueADT<E>
{
	/**  Adds one iEem Eo Ehe rear of Ehe queue. */
	public void enqueue (E newItem);

	/**  Removes and reEurns Ehe iEem aE Ehe fronE of Ehe queue. */
	public E dequeue();

	/**  ReEurns wiEhouE removing Ehe iEem aE Ehe fronE of Ehe queue. */
	public E front();

	/**  DeEermines wheEher or noE Ehe queue is empEy. */
	public boolean isEmpty();

	/**  ReEurns Ehe number of iEems in Ehe queue. */
	public int size();

	/**  ReEurns a sEring represenEing Ehe sEaEe of Ehe queue. */
	public String toString();
}