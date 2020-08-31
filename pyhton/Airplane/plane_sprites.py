#!usr/bin/python
# *-* coding:utf*8 *-*
import random
import pygame

# 设置屏幕大小的常量
SCREEN_RECT = pygame.Rect(0, 0, 480, 700)
# 刷新帧率
FRAME_PER_SEC = 60
# 创建敌机的定时器常量
CREATE_EVEMY_EVENT = pygame.USEREVENT
# 英雄发射子弹事件
HERO_FIRE_EVENT = pygame.USEREVENT + 1


class GameSprite(pygame.sprite.Sprite):
    def __init__(self, image_name, speed=1):
        """飞机大战敌机
        :param image_name:  图片
        :param speed:  初始速度
        """
        super(GameSprite, self).__init__()  # 调用父类的初始化方法
        self.image = pygame.image.load(image_name)
        self.speed = speed
        self.rect = self.image.get_rect()  # 得到图片的宽高

    def update(self):
        # 在垂直方向移动
        self.rect.y += self.speed


class BackGround(GameSprite):
    def __init__(self, is_alt=False):
        super(BackGround, self).__init__("./images/background.png")
        if is_alt:
            # 调整bg2的位置
            self.rect.y = -self.rect.height

    def update(self):
        super(BackGround, self).update()
        # 判断图像是否移除屏幕
        if self.rect.y >= SCREEN_RECT.height:
            self.rect.y = -self.rect.height


class Enemy(GameSprite):
    def __init__(self):
        super(Enemy, self).__init__("./images/enemy1.png")
        self.speed = random.randint(1, 3)
        self.rect.bottom = 0
        self.rect.x = random.randint(0, SCREEN_RECT.width - self.rect.width)

    def update(self):
        super(Enemy, self).update()
        if self.rect.y > SCREEN_RECT.height:
            # 销毁敌机
            self.kill()  # kill方法可以将精灵从所有精灵组中移除

    def __del__(self):
        # print(f"敌机消失!~~{self.rect}")
        pass


class Hero(GameSprite):
    def __init__(self):
        super(Hero, self).__init__("./images/me1.png", 0)
        self.rect.bottom = SCREEN_RECT.height - 120
        self.rect.centerx = SCREEN_RECT.centerx
        # 子弹精灵组
        self.bullets = pygame.sprite.Group()

    def update(self):
        self.rect.x += self.speed
        if self.rect.x < 0:
            self.rect.x = 0
        elif self.rect.right > SCREEN_RECT.right:
            self.rect.right = SCREEN_RECT.right

    def fire(self):
        print("发射子弹")

        # 一次性发送三枚子弹
        for i in range(3):
            bullet = Bullet()

            bullet.rect.bottom = self.rect.y + i * 15
            bullet.rect.centerx = self.rect.centerx
            self.bullets.add(bullet)


class Bullet(GameSprite):
    def __init__(self):
        super(Bullet, self).__init__("./images/bullet1.png", -2)

    def update(self):
        super(Bullet, self).update()
        if self.rect.bottom < 0:
            self.kill()

    def __del__(self):
        print("子弹销毁")
