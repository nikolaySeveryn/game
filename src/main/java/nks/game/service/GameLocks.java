package nks.game.service;

import java.util.HashMap;
import java.util.Map;

import nks.game.domain.Game;

//@Bean
//@Singleton
public class GameLocks {

	private volatile Map<Game, Object> locks = new HashMap<>();
	
	public Object getLock(Game game) {
		if(! locks.containsKey(game)) {
			synchronized (locks) {
				if(! locks.containsKey(game)) {
					return locks.put(game, new Object());
				}
			}
		}
		return locks.get(game);
	}
}
