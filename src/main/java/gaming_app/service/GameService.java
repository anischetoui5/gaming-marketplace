package gaming_app.service;


import gaming_app.model.Game;
import gaming_app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
        
        game.setName(gameDetails.getName());
        game.setDescription(gameDetails.getDescription());
        game.setDeveloper(gameDetails.getDeveloper());
        game.setReleaseDate(gameDetails.getReleaseDate());
        game.setBasePrice(gameDetails.getBasePrice());
        
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
        gameRepository.delete(game);
    }

    public List<Game> searchGames(String name) {
        return gameRepository.findByNameContainingIgnoreCase(name);
    }
}
