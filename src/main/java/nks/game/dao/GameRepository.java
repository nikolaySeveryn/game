package nks.game.dao;

import nks.game.domain.Game;

public interface GameRepository {

	public Game get(Long id);
	
	public void save(Game game);
}
