package com.levi9.connect4.controllers;

import com.levi9.connect4.entity.Board;
import com.levi9.connect4.entity.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.levi9.connect4.search.MinMax;

@RestController
@RequestMapping("/api")
public class GameController
{
	@Autowired
	private MinMax minMax;
	
	@GetMapping("/moves")
	public Move getMove(@RequestParam String state, @RequestParam int playerToMove)
	{
		Board  board = new Board(state);
		return minMax.getNextMove(board, playerToMove);
	}

}
