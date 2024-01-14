import java.time.LocalDateTime;
import java.time.Duration;

public class Evento
{
	//Class
	private static LocalDateTime calculaFim (LocalDateTime inicio, long duracao)
	{
		LocalDateTime fim;

		fim = LocalDateTime.from (inicio);
		fim.plusMinutes (duracao);

		return fim;
	}

	private static long calculaDuracao (LocalDateTime inicio, LocalDateTime fim)
	{
		long duracao;

		duracao = 0;
		try {
			duracao = Duration.between (inicio, fim).toMinutes();
		} catch (ArithmeticException e) {
			//overflow
			//1. Keep 0;
			//2. Throw EventoHoldingMinutesException (inicio, fim).
		}

		return duracao;
	}

	//Instance
	protected LocalDateTime inicio;
	protected LocalDateTime fim;
	protected long duracao; //minutos
	protected String nome;

	public Evento (LocalDateTime inicio, long duracao, String nome)
	{
		this.inicio = LocalDateTime.from (inicio);
		this.duracao = duracao;
		this.nome = nome;

		this.fim = Evento.calculaFim (inicio, duracao);
	}

	public Evento (LocalDateTime inicio, LocalDateTime fim, String nome)
	{
		this.inicio = LocalDateTime.from (inicio);
		this.fim = LocalDateTime.from (fim);
		this.nome = nome;

		this.duracao = Evento.calculaDuracao (inicio, fim);
	}

	public Evento (Evento e)
	{
		this.inicio = e.getInicio ();
		this.fim = e.getFim ();
		this.duracao = e.getDuracao ();
		this.nome = e.getNome ();
	}

	//Setters
	public void setInicio (LocalDateTime inicio)
	{
		this.inicio = LocalDateTime.from (inicio);

		//collateral
		this.duracao = Evento.calculaDuracao (inicio, this.fim);
	}

	public void setFim (LocalDateTime fim)
	{
		this.fim = LocalDateTime.from (fim);

		//collateral
		this.duracao = Evento.calculaDuracao (this.inicio, fim);
	}

	public void setDuracao (long duracao)
	{
		this.duracao = duracao;

		//collateral
		this.fim = Evento.calculaFim (this.inicio, duracao);
	}

	public void setNome (String nome)
	{
		this.nome = nome;
	}

	//Getters
	public LocalDateTime getInicio ()
	{
		return LocalDateTime.from (this.inicio);
	}

	public LocalDateTime getFim ()
	{
		return LocalDateTime.from (this.fim);
	}

	public long getDuracao ()
	{
		return this.duracao;
	}

	public String getNome ()
	{
		return this.nome;
	}

	//Object
	@Override
	public Evento clone ()
	{
		return new Evento (this);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o)
			return true;

		if ( (o == null) || (this.getClass() != o.getClass()) )
			return false;

		Evento e = (Evento) o;

		return ( this.inicio.equals (e.getInicio())
				&& this.fim.equals (e.getFim())
				&& this.duracao == e.getDuracao()
				&& this.nome.equals (e.getNome())
			);
	}

	@Override
	public String toString ()
	{
		String s;

		s = "";
		s += inicio + " " + fim + " " + duracao + " " + nome;

		return s;
	}

	//main
	public static void main (String args[])
	{
		LocalDateTime inicio, fim;
		String nome;
		Evento e;

		//Teste 00:30 - 00:00
		inicio = LocalDateTime.of (2024, 01, 20, 00, 00);
		fim = LocalDateTime.of (2024, 01, 20, 00, 30);
		nome = "Duration between 00:00 a.m. and 00:30 p.m.";
		e = new Evento (inicio, fim, nome);
		System.out.println (e);
		//Teste ok.


		//Teste Clone
		Evento e1;

		e1 = e.clone();
		System.out.println (e1.equals (e));
		//Teste ok.


		//Teste Clone + SetNome
		Evento e2;

		e2 = e.clone();
		e2.setNome ("Nome");
		System.out.println (e2.equals (e));
		//Teste ok.
	}

}