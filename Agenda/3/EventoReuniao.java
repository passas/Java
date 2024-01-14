import java.time.LocalDateTime;

import java.util.Map;
import java.util.HashMap;

import java.util.List;
import java.util.ArrayList;

public class EventoReuniao extends Evento
{
	private String local;
	private String assunto;
	private Map<String, String> participantes;
	private List<String> necessidades;

	public EventoReuniao (LocalDateTime inicio, long duracao, String nome
							,String local, String assunto)
	{
		super (inicio, duracao, nome);

		this.local = local;
		this.assunto = assunto;
		this.participantes = new HashMap<>();
		this.necessidades = new ArrayList<>();
	}

	public EventoReuniao (LocalDateTime inicio, LocalDateTime fim, String nome
							,String local, String assunto)
	{
		super (inicio, fim, nome);

		this.local = local;
		this.assunto = assunto;
		this.participantes = new HashMap<>();
		this.necessidades = new ArrayList<>();
	}

	public EventoReuniao (EventoReuniao e)
	{
		super (e);

		this.local = e.getLocal ();
		this.assunto = e.getAssunto ();
		this.participantes = e.getParticipantes ();
		this.necessidades = e.getNecessidades ();
	}

	//Setters
	public void setLocal (String local)
	{
		this.local = local;
	}

	public void setAssunto (String assunto)
	{
		this.assunto = assunto;
	}

	//Getters
	public String getLocal ()
	{
		return this.local;
	}

	public String getAssunto ()
	{
		return this.assunto;
	}

	public Map<String, String> getParticipantes ()
	{
		Map<String, String> participantes;

		participantes = new HashMap<>();
		for (Map.Entry<String,String> e : this.participantes.entrySet())
			participantes.put (e.getKey(), e.getValue());

		return participantes;
	}

	public List<String> getNecessidades ()
	{
		List<String> necessidades;

		necessidades = new ArrayList<>();
		for (String n : this.necessidades)
			necessidades.add (n);

		return necessidades;
	}

	//Object
	@Override
	public EventoReuniao clone ()
	{
		return new EventoReuniao (this);
	}

	@Override
	public boolean equals (Object o)
	{
		if (this == o)
			return true;

		if ( (o == null) || (this.getClass() != o.getClass()) )
			return false;

		EventoReuniao er = (EventoReuniao) o;

		return ( super.equals (er)
				&& this.local.equals (er.getLocal())
				&& this.assunto.equals (er.getAssunto())
				&& this.participantes.entrySet().equals (er.getParticipantes().entrySet())
				&& this.necessidades.equals (er.getNecessidades())
			);
	}

	@Override
	public String toString ()
	{
		String s;

		s = super.toString () + " ";

		s += local + " " + assunto + " ";
		for (Map.Entry<String,String> e : this.participantes.entrySet())
			s += e.getKey() + " " + e.getValue() + " ";
		for (String necessidade : this.necessidades)
			s += necessidade + " ";

		return s;
	}

	//API
	public void addParticipante (String participante, String contacto)
	{
		this.participantes.put (participante, contacto);
	}

	public void addNecessidade (String necessidade)
	{
		this.necessidades.add (necessidade);
	}

	//main
	public static void main (String args[])
	{
		LocalDateTime inicio, fim;
		String nome, local, assunto;
		EventoReuniao e;

		//Teste Hierarquia
		inicio = LocalDateTime.of (2024, 01, 20, 00, 00);
		fim = LocalDateTime.of (2024, 01, 20, 00, 30);
		nome = "Duration between 00:00 a.m. and 00:30 p.m.";
		local = "https://zoom.call.101.com";
		assunto = "Futuro";
		e = new EventoReuniao (inicio, fim, nome, local, assunto);
		e.addParticipante ("Sergio", "91xxxxxxx");
		e.addNecessidade ("Desenvolver codigo");
		System.out.println (e);
		//Teste ok.


		//Teste Clone
		EventoReuniao e1;

		e1 = e.clone();
		System.out.println ( e.equals (e1) );
		//Teste ok.


		//Teste Clone + SetNome
		EventoReuniao e2;

		e2 = e.clone ();
		e2.setNome ("Nome");
		System.out.println ( e2.equals (e) );
		//Teste ok.
	}

}