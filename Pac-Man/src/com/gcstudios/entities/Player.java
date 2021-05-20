package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	public int lastDir = 1;
	public BufferedImage sprite_left;
	public BufferedImage sprite_up;
	public boolean right,up,left,down;

	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = -1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
		}
		cathFood();
		if(Game.frut_destroy == Game.frut_atual) {
			//System.out.println("You Winer");
			World.restartGame();
			
		}
	}
	public void cathFood() {
		for(int i=0; i< Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Food) {
				if(Entity.isColidding(this, current)) {
					Game.frut_atual++;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}
	public void render(Graphics g) {
		if(lastDir == 1) {
			super.render(g);		
		}else {
			g.drawImage(sprite_left ,this.getX() - Camera.x,this.getY() - Camera.y,null);
			
		} 
	}

}
