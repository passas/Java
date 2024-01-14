import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;


public class EventoTrabalho extends Evento
{
	private List<String> ferramentas;
	private List<String> necessidades;
	
	public EventoTrabalho (LocalDateTime inicio, long duracao, String nome)
	{
		super (inicio, duracao, nome);

		this.ferramentas = new ArrayList<>();
		this.necessidades = new ArrayList<>();
	}

	public EventoTrabalho (LocalDateTime inicio, LocalDateTime fim, String nome)
	{
		super (inicio, fim, nome);

		this.ferramentas = new ArrayList<>();
		this.necessidades = new ArrayList<>();
	}

	public EventoTrabalho (EventoTrabalho e)
	{
		super (e);

		this.ferramentas = e.getFerramentas ();
		this.necessidades = e.getNecessidades ();
	}

	//Getters
	public List<String> getFerramentas ()
	{
		List<String> ferramentas;
		
		ferramentas = new ArrayList <> ();
		for (String f : this.ferramentas)
			ferramentas.add (f);

		return ferramentas;	
	}

	public List<String> getNecessidades ()
	{
		List<String> necessidades;
		
		necessidades = new ArrayList <> ();
		for (String n : this.necessidades)
			necessidades.add (n);

		return necessidades;	
	}

	//Object
	@Override
	public EventoTrabalho clone ()
	{
		return new EventoTrabalho (this);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o)
			return true;

		if ( (o == null) || (this.getClass() != o.getClass()) )
			return false;

		EventoTrabalho et = (EventoTrabalho) o;

		return ( super.equals (et)
				&& this.ferramentas.equals(et.getFerramentas())
				&& this.necessidades.equals(et.getNecessidades())
			);
	}

	@Override
	public String toString ()
	{
		String s;

		s = super.toString () + " ";
		for (String ferramenta : this.ferramentas)
			s += ferramenta + " ";
		for (String necessidade : this.necessidades)
			s += necessidade + " ";

		return s;
	}

	//API
	public void addNecessidade (String necessidade)
	{
		this.necessidades.add (necessidade);
	}

	public void addFerramenta (String ferramenta)
	{
		this.ferramentas.add (ferramenta);
	}

	//main
	public static void main (String args[])
	{
		LocalDateTime inicio, fim;
		String nome;
		EventoTrabalho e;

		//Teste Hierarquia
		inicio = LocalDateTime.of (2024, 01, 20, 00, 00);
		fim = LocalDateTime.of (2024, 01, 20, 00, 30);
		nome = "Duration between 00:00 a.m. and 00:30 p.m.";
		e = new EventoTrabalho (inicio, fim, nome);
		e.addNecessidade ("Desenvolver codigo");
		e.addFerramenta ("Computador");
		System.out.println (e);
		//Teste ok.


		//Teste Clone
		EventoTrabalho e1;

		e1 = e.clone();
		System.out.println ( e.equals (e1) );
		//Teste ok.


		//Teste Clone + SetNome
		EventoTrabalho e2;

		e2 = e.clone ();
		e2.setNome ("Nome");
		System.out.println ( e2.equals (e) );
		//Teste ok.


		//Teste Clone + addNecessidade
		EventoTrabalho e3;

		e3 = e.clone ();
		e3.addNecessidade ("Esclarecer codigo: funcao X");
		System.out.println ( e3.equals (e) );
		//Teste ok.
	}

}