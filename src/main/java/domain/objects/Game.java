package domain.objects;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.Observateur;
import domain.Visiteur;
import persistence.UnitOfWork;
import persistence.factories.FactoryListPlayer;
import persistence.factories.FactoryListTerritory;
import persistence.factories.FactoryPlayer;
import persistence.factories.VirtualProxyBuilder;


public class Game implements IDomainObject {
	
	
	private int id;
	private String name;
	private int currentPlayer;
	private int turnNumber;
	private String status;
	private int turnRessources;
	private int fieldRessources;
	private List<Player> listPlayers;
	private HashMap<Player,Integer> playerResources;
	private List<Territory> listTerritories;
	private List<Observateur> obs;
	
	public Game(String name, int currentPlayer, int turnNumber, String status, int turnRessources, int fieldRessources)
	{
		this.name = name;
		this.currentPlayer = currentPlayer;
		this.turnNumber = turnNumber;
		this.status = status;
		this.playerResources = new HashMap<>();
		this.turnRessources = turnRessources;
		this.fieldRessources = fieldRessources;
		this.listPlayers = new VirtualProxyBuilder<List<Player>>(List.class, new FactoryListPlayer(id)).getProxy();
		this.listTerritories = new  VirtualProxyBuilder<List<Territory>>(List.class, new FactoryListTerritory(id)).getProxy();
		this.obs = new ArrayList<Observateur>();
		
	}
	
	public Game(){
		
	}
	
	

	
	


	public HashMap<Player, Integer> getPlayerResources() {
		return playerResources;
	}



	public void setPlayerResources(HashMap<Player, Integer> playerResources) {
		this.playerResources = playerResources;
	}



	public int getIdGame() {
		return id;
	}



	public void setIdGame(int id) {
		this.id = id;
		this.listPlayers = new VirtualProxyBuilder<List<Player>>(List.class, new FactoryListPlayer(id)).getProxy();
		this.listTerritories = new  VirtualProxyBuilder<List<Territory>>(List.class, new FactoryListTerritory(id)).getProxy();
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	



	public Player getCurrentPlayer() {
		try {
			return new FactoryPlayer(currentPlayer).create();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}


	

	public int getTurnRessources() {
		return turnRessources;
	}



	public void setTurnRessources(int turnRessources) {
		this.turnRessources = turnRessources;
	}



	public int getFieldRessources() {
		return fieldRessources;
	}



	public void setFieldRessources(int fieldRessources) {
		this.fieldRessources = fieldRessources;
	}



	public List<Territory> getListTerritories() {
		return listTerritories;
	}



	public void setListTerritories(List<Territory> listTerritories) {
		this.listTerritories = listTerritories;
	}


	//TODO  Main method ? 
	public void start()
	{
		//setState(true);
		//this.listPlayers.get(0).setTurn(true);
	}

	public List<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(List<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}

	

	@Override
	public void add(Observateur o) {
		obs.add(o);
	}

	@Override
	public void notifier() {
		for (Observateur o : obs) {
			o.action(this);
		}
	}

	@Override
	public void accepter(Visiteur v) {
		v.visiter(this);
	}
}
