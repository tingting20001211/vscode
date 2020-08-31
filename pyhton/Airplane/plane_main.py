#!usr/bin/python
# *-* coding:utf-8 *-*

import pygame
from plane_sprites import *


class PlaneGame(object):
    """主游戏类"""
    def __init__(self):
        print("游戏初始化中...")
        # 游戏窗口
        self.screen = pygame.display.set_mode(SCREEN_RECT.size)
        # 封装时钟
        self.clock = pygame.time.Clock()
        # 创建精灵组(私有方法)
        self.__create_sprites()
        # 设置定时器事件 -- 创建敌机
        pygame.time.set_timer(CREATE_EVEMY_EVENT, 1000)
        # 设置子弹发射的事件 -- 子弹发射
        pygame.time.set_timer(HERO_FIRE_EVENT, 500)

    # 创建精灵
    def __create_sprites(self):
        # 创建背景的精灵和精灵组
        bg1 = BackGround()
        bg2 = BackGround(True)
        self.back_group = pygame.sprite.Group(bg1, bg2)  # 创建精灵组
        # 创建敌机精灵组
        self.enemy_group = pygame.sprite.Group()
        # 创造英雄精灵组
        self.hero = Hero()
        self.hero_group = pygame.sprite.Group(self.hero)

    # 事件监听
    def __event_handler(self):
        for event in pygame.event.get():
            # 判断是否退出
            if event.type == pygame.QUIT:
                PlaneGame.__game_over()  # 通过类名的方式调用静态方法
            elif event.type == CREATE_EVEMY_EVENT:
                # print("出现新的敌机")
                # 创建敌机
                enemy = Enemy()
                self.enemy_group.add(enemy)
            elif event.type == HERO_FIRE_EVENT:
                self.hero.fire()
            # 该方式的弊端，如果长按键盘不放，是不会触发的
            # elif event.type == pygame.KEYDOWN and event.key == pygame.K_RIGHT:
            #     print("向右移动")
        # 键盘模块可以 --> 长按
        # 不在for循环内部，使用键盘提供的方法，获取键盘的按键
        key_pressed = pygame.key.get_pressed()  # 返回值是键盘的元组
        # 判断元组中对应的按键索引值 1代表按下
        if key_pressed[pygame.K_RIGHT]:
            self.hero.speed = 3
        elif key_pressed[pygame.K_LEFT]:
            self.hero.speed = -3
        else:
            self.hero.speed = 0

    # 碰撞检测
    def __check_collide(self):
        # TODO(YBT, 21.25 6.21)  碰撞检测
        # 子弹撞毁敌机
        pygame.sprite.groupcollide(self.hero.bullets, self.enemy_group, True, True)
        # 敌机撞毁英雄
        enemies = pygame.sprite.spritecollide(self.hero, self.enemy_group, True)
        # 判断列表是否有内容
        if len(enemies) > 0:
            self.hero.kill()
            PlaneGame.__game_over()

    # 更新绘制精灵组
    def __update_sprites(self):
        self.back_group.update()
        self.back_group.draw(self.screen)

        self.enemy_group.update()
        self.enemy_group.draw(self.screen)

        self.hero_group.update()
        self.hero_group.draw(self.screen)

        self.hero.bullets.update()
        self.hero.bullets.draw(self.screen)

    @staticmethod
    def __game_over():
        print("游戏结束")
        pygame.quit()
        exit()

    def start_game(self):
        print("游戏开始")
        while True:
            # 调整帧率,指定循环体内部代码执行频率
            self.clock.tick(FRAME_PER_SEC)
            # 事件监听
            self.__event_handler()
            # 碰撞检测
            self.__check_collide()
            # 更新绘制精灵组
            self.__update_sprites()
            # 更新显示
            pygame.display.update()


if __name__ == '__main__':
    game = PlaneGame()
    game.start_game()

