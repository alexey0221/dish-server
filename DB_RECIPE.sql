PGDMP  /    )                |         	   ck_recipe    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24619 	   ck_recipe    DATABASE     }   CREATE DATABASE ck_recipe WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE ck_recipe;
                postgres    false            �            1259    24640    comment    TABLE     �   CREATE TABLE public.comment (
    id_comment integer NOT NULL,
    user_name character varying NOT NULL,
    text text NOT NULL,
    id_dish integer NOT NULL
);
    DROP TABLE public.comment;
       public         heap    postgres    false            �            1259    24626    dish    TABLE       CREATE TABLE public.dish (
    id_dish integer NOT NULL,
    name_dish character varying(40) NOT NULL,
    ingredients character varying,
    recipe text,
    tags character varying,
    rating real,
    photo character varying,
    number_of_ratings integer
);
    DROP TABLE public.dish;
       public         heap    postgres    false            �            1259    24647    user_rating    TABLE     �   CREATE TABLE public.user_rating (
    user_name character varying NOT NULL,
    id_dish integer NOT NULL,
    user_rating real
);
    DROP TABLE public.user_rating;
       public         heap    postgres    false            �            1259    24633    users    TABLE     �   CREATE TABLE public.users (
    user_name character varying(10) NOT NULL,
    email character varying(20) NOT NULL,
    passwd character varying(512),
    avatar bytea,
    collection integer[]
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    24640    comment 
   TABLE DATA           G   COPY public.comment (id_comment, user_name, text, id_dish) FROM stdin;
    public          postgres    false    217   �       �          0    24626    dish 
   TABLE DATA           o   COPY public.dish (id_dish, name_dish, ingredients, recipe, tags, rating, photo, number_of_ratings) FROM stdin;
    public          postgres    false    215   <       �          0    24647    user_rating 
   TABLE DATA           F   COPY public.user_rating (user_name, id_dish, user_rating) FROM stdin;
    public          postgres    false    218   �*       �          0    24633    users 
   TABLE DATA           M   COPY public.users (user_name, email, passwd, avatar, collection) FROM stdin;
    public          postgres    false    216   �*       *           2606    24646    comment comment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id_comment);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    217            &           2606    24632    dish dish_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.dish
    ADD CONSTRAINT dish_pkey PRIMARY KEY (id_dish);
 8   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_pkey;
       public            postgres    false    215            (           2606    24639    users user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_name);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT user_pkey;
       public            postgres    false    216            �   �  x�UR�N�P<�~�K�V�
��<{A��hl��୔& D/^�
m������9�� ��v����l=t���МTSơk)������R�}�ೠ��GTqd9pH��Y��1.����AO�gf�ι�>�=v}�<90[�,�F�(1�t��"�XJ�<�T�]��*dE)�M�i�+������/@S0J�oڐ��N�B|L��)�Q"RRa=A$����(�rƮ�,���F��Q W��q�k;m����ۧ��7�A�%9���P2��B�Q�1�J�Lh�nvY-�\���ԚP"�P\6��⠠QI�Ty�5�x���=CYk��5֖�F5��t���3�����~��@�#)�Dm��#��T.!����=�vP]D	�V]h�#9	�����\8�g�����      �      x��\Ko[W�^3�⢁d�Ԉ�d;�f6�����CČdɐc��q�=V�1�F&�ēi� ���+J������T}Uu^�RR'�"�2y�yԩ�W_�a�V�X��{��t�8���_�?��b�5�Ϧ{+��	}sF�{B���szqR���ْ>yQ�;��WY1�?���b<}^�f��L���O���u�Lw���'4&=���w����h�C�Rz���F��PÌ�N��s{����cY���b�Z�>O��^k�g��M�	=:)���uz�_��h;�x�#���t�EA�؟�� �!Ƀަ�����/��>���>��7Y.*�"���C�/���9�e������h%�,u�o>���/�.��Q��x�5��d��8��ߟ��z�2���
��Q1�YX	�y�Ĉv���#�P!�c��'B�3��{�
Kw�O��k�ܱF$_>8ZȿC��t��
��H�=����a=�i*Ϙ*��b{�g��	-���	�x�����+��,�Y�̇}�.��?`�}~��48���M�-��r�mON\�N��ڝ�ҴD��2�����Y�J''�;�x��s��2�OM �c�ң���^���P�!^�`)Gxym29�ْ�e�y'$8"�|�1i[^�8��N��������x��|�2��Ќ �!�K�Nz}��0V��������f�찏�x���chU����_Ȱ8���q���!����%�S[���G++�����ZY����v?_Y���������{�>�����A<�n��<z��������fg{���'���kŷXqN&З��$<�,=�<6@��%�I�5��L^���?~3�!�ы'����5�����gvpj���9�:β�E��=:(��'<�-�a��F�+������/҇V@�xԉ��!LA��)��C��&���)�/�<��|�y]@I���RS�=S>X�U#;VV�`1f�6V�Dv��H�7�ў�p��h^bՑ�U:#m���8�f���X- k.�~��u,�v�)��k���P��dKވu��Or�,�S��>����>�[$=/g��J�Z�Xa��珽��{�g�Gg�ƽ�#�5���{�M��̶H:&?U���7�q����;/=�_��л��]oo������jO]Ss��>�-pĎ�����~ޟ��/gKG��w��ʡG��)���I��������kel�c���� �ɼ؏l���'��.f�sC�E���I� eѓþ۱`)��l��~��`!��W��Wb.��_}�����Oy��Q"F�d��:��]�� e꼪<��x� W��}�`�{��L�h��V7(p���̐.�V�Ј�?Gi`�g���1�3�¼ �����u1`�����v�'.��� �-�^$���e	[��iޭ����ۑ˩�PްC��%Ƹ��������Z�}�x�Y_ﭪ=�9�'ê6��v��!��� y
��?0����ѥ�Y�9$/G�W�!�n�g"!6p�C�'�
\�&��~5,�<KVb�u�s���F�F�>R�e�o6��ˬ/��qZQ�aN_�G�|�L�:���z.�� �~{�Cq��i^2;G%�܍�p�(��S¹j#�"t�K�S�mL��˵9M��dˋJ
v������䚛����X1"�1�w�Y�j�$��������!=�Gd�9���ꔱQ9}�e���U��D�k�/t6W7���ޣ���R�I ��/S�)�JC���8J5]8� � a�Y8���������sC�P��kA3�uB9���2|r���I�;a&kQTFAՈ9Z�D���ra^�q-k�=8!�0Y�z����ũ�lz�#�5)�5��wlv]&Wdx�8�j���`v���ސ";^�����J(Đ��p�������]�l~���x�CQ�`c+����}��}���4d�����zWi��9�*͹�l�S���-F�._��=�B���SŴ�S�{�P{%>ߥZ�U��� ��U�eş"w^����~�R>!��wj�BD�ň_1q,�;,���\�����S��"�O��Kl��`
�DNp-��E�&.�QZQ�9{�G���$�:���,PČG;>��,�_�M��E����l�R�T��F�'.0�|�7hh�l�t>�o���ݮ��I�,g��p�Zy:q�h6�ɝ�(t�#�c�HD���1+Mc��c�!��=eow�A��]H�,�>��΁��p�Jʸ��`�+�eAѐ�b�j+S�}���9'��p���ّ!���� ~��R���K����j;�&���\�@�dDLr�������Mj�B���%0�R��
��ar�a5�bG��ڝ��x.��n(FK"%���s ���h_d ��*�s�.������}88'�\��"�]����	�@�PeO����8;D��MIqO �'��X�o�A�fL*�c.��>kZd@�:�bp�K+�X:r|�6�������=�͏C�-s���Z�u����`��g����/�&ɹh��l<�߭��ү�����\��^���7����V�Q�Y�b��̫���YLC��df(�Bc�Ec���-���KG�O"`]�J�X���r8�A��A�-)���Pp,�N�OFx��B�Ł������AR�J-o�,�W��ZP(g/ў�O���I=G�&2��H*��J���s�;�9>d� G.{;�Y�@N/ʚ]"\�?�R��f�u�L�<,=a���(��QӱD�%֯^]XUiCD&��,��^�q{����ܭE l���5#�c+Ui]lȨ��R�9�o161徺<�XPSkݓ�2��	��������˯��bD������ŗ��;<�C���o߃F��D�x�d�j��:H�.�2S�o���b��g@��1Q�� .X۪b�f��!�D|�	Z�F*j��U44���K�r9�E��+dV�@������W���^�����c�YZRL���]I��G�U�_T1kI҅�^�[�M	��=�GT��tڜj{/���J�����6b��0�X�@w/��/�Cb$�d�V2<"�Q�K4!���z���x8�+b+�.����/�:��g�Beܙri�C%^�5H·�����z��Σ�����1�0R�o��
�/J���o��4�u������0?����⯛�HC��q�yɧ�ډ<�,5�#�M_��e��}=|���䣌�I>��;���x{0ϧj)$ϓ���p ŽIkm����t�|��f�H�֪��d	�!�W`���"!�YR��<���x���ؕY�d�C�����u�쵊*����RZ���>ޅ��5,�,mu��Gր�wV{5�4<Vԇ��&={:b=�����������"�9�w��ǚ�W؃����g��b4��ԋ���
 #�B�G�F[����Z$~�Vt+�f��9+S�Yq�[Y$��l���z2/28��������Eʾ#�v(��M{�doV�bO@r_��(XTL��W�#w��/½k�����O��k.h�+;��:K�?!��w�z懿e@��P��@O�Z�g�Z�%���W.kk��V��k�d�98��]5��L`! ���U"N8'���d�z�"�R.[���0<u\1�q`��]N>s�E��͒�aܕt8Ty��Y�I\`P�]��'�p�EqRE�-���*$�v2Y/��n󊫸횾57�j9@��е�*r��L
���)]���^�(�~Yi��1�WI�kf�ܩ9ʉ6�HZ:����/"�z��e��D��T�j��݉��B�S���_�n7Ԣ9���9'b�'�p���X�p&� Jw�X,�x��p0�w@ O�����2Ǘh:]�9�1bW��^������&�@��{AJa��O��{F��	�%h�k�C�@�|e��P�2)��җ�g�F2k��MpX�L/�(x��f�0)q�+�7d����$�*W޴_�]�Z\^h_HU�����qF�ג�X�ڢ�p��Z 7	  �_���ۭ!�d�T/��a�����P����6��潯�}�B��������I]��ܸ�-�#:U�l	�Rl/3���gY�ᎵQ%��ႆ�.��#e�ҷ`�*�g��]*|a�Q��|�Y�P;�GUm�z��n8�?�cҰ}���m� �;�nG�Ma��U�Xq�h�r�#s��s����a��n8Ve��"�v|	bX!O�8�\�_�+e��6?����ʥ��E�!��}4�{�l{��83P���%����)���`�7\�3p]e���
���j-�*j�����ʪbzR8��ׇ�a�q�w=Km��ұ;f�lUc|i�)Fػvs=�����?h�w۽/ڏ�[�3i��8/��`H���^ǃZ������F�RR�TM��>�l�P3����͡�tK�4�c�k�i�G�]�J��� U��:_��Y�RC3H��|�0Ɛr+ϐ;���^6��
���
���?�`�#Y�����^5>�}�a��n׋i�q9 )ʑ�������$�;��"|	Y���\�8�T9_��&u�����w�^�;�� O�D��m�q�X��U�H�]�Bz�[�;Ë�rMy�.�b��xn$5��Cf����괷�6�x��av���\���'�����+�L�=J+�CY��r}��)�2���=��1������$� �_�vFEe)��[aJJg�{`ٵ�����P�iZ88�JI��Tc�P����DÈ�
�P����.�d��;�1��� ���2������[�V隀z=I���t#���M�ޭ:�/B�*���Dd��G;B����δ����3�$�Z���>�34cy��A!�>��k(\8��Ǳ�Q�\��B6f��P�����9�:�}MO^�7��k��`(�;ֱ��L`>�֧~)h�� * x�*����*T��z�O��b��ِ��!.�i�+�FnQ2���)�(x\�58A�yR����s��PH�i����ԩz[�����]��s������[F�J�*�n,.�E�<�K<ޭ�1�s%��Ve_@6�\h4n�n	�*u7F��벡�
��h����:��(�NE�3
�zn%�����ƅ�b�
.�]ޝ��R�����	�-_W	�0��w���ժxN�i*���C���گ�I��z�5�*��ޘ	���2��z�������k]�kޮ1ǥ�J���Yί����g������/hw�`�Bl|�e@�#[�l[��<8����ړ�Yyoſ?x���4���L�iF�\8������k8%�G���Ii���GRy�"�sm!S�i�;a>FV���X����fsqy������Jit`w7���d��$P��w/3d9��%i��yg�~;9�Ҵ�t��Vpg?
s��f6\�h<Љ↯lu׺�v�_�n|�~�^�ls{�Z��w8`�r���~Zɀ�(4F��#�z��y�MU����Ң�fA���Ͳ�R�s����Bo}��԰[~��=�<"x��#M����ھ�"��a�ľT���[2s��J�Q�q��+H�[�̓3�^s�߻�rW���+�=�՜��L{^�:���7b�9���s;��dw����%H4�^:���h��o��6�z�{�vo{���y��.��+uy�~�$[���:��`��&��Oj��ܕ5�V��^dK��?ȀJzL�:Br�wL��%��9aK'`���aG��v�T���K��-A>xVȝ�g��{H�r0q��2ԥdc��2��
�e�;�%`j7T�4`б�$�&k�L��P�Ť����|?��1U2L�u|�;*�=ŗM������WwX�|����4YZ?�OO
}��Z���5�X�CcW���^�qm^�����?��K�+cџ�P�(]2sdĭ@w7�#����2λ�{�r-î<��F����u�V�8��������yͪͅ��mF�������޽ާ���vg���}�*(Z���	�&T�w��U9���-
�aD�G��|����'B���s�Sw�Â���P�����T5""b���MQ!�3�H|m䐄��ٯ;�A�JVY/��G��Ʌ9ߊy%<�]}�@��;cm�n���?����A<9��~)��ƪ��O�k�:��,��z�c낃�����L�.O�qo��ɦ����^.8,�2���>`��DƯIO�g��!}w�� ����	�{<:s[�~w�_�K�Ҵ4��1떍��o��V��P�r_�}�Z��q�?�aI)7H\.�8A�4Gz�+{}��!�a/@��ߺ��B�]�畝B�
�m���w{�{o�l8���vz�V�^����x�E�������/V�?����0ѬB      �      x������ � �      �   �   x�M�=�0�g�+BZ~6P1tf1!D�#RԢ���IQ�Ŷ޳�g�<�K @��fU$yK�M/�;^W��+q�'`�>:g65޸e��o�<`Y��vԞ��َ�FM�K��o'.�*�#�;2f�z�,���D��/Y^�G2��	}5;�����~�B     