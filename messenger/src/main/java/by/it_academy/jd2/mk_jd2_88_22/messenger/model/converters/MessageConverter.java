package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class MessageConverter implements IConverter<Message, MessageEntity> {

    @Override
    public Message convert(MessageEntity entity) {
        return Message.Builder.build()
                .setId(entity.getId())
                .setRecipient(entity.getRecipient())
                .setSender(entity.getSender())
                .setMessage(entity.getMessage())
                .setDate(entity.getDate())
                .createMessage();
    }

    @Override
    public MessageEntity convertBackward(Message message) {
        return MessageEntity.Builder.build()
                .setId(message.getId())
                .setRecipient(message.getRecipient())
                .setSender(message.getSender())
                .setMessage(message.getMessage())
                .setDate(message.getDate())
                .createMessage();
    }
}
